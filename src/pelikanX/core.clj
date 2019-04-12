(ns pelikanX.core
  (:require [cljfx.api :as fx])
  (:import (javafx.scene.input KeyCode)))

(def *state
  (atom {:showing true
         :pelikan-input-value "pelikanX"}))

(defn pelikan-input [{:keys [value]}]
  {:fx/type :text-field
   :on-text-changed {:event/type ::text-changed}
   :on-key-pressed {:event/type ::key-pressed}
   :text value})

(defn root [{:keys [showing pelikan-input-value data]}]
  {:fx/type :stage
   :showing showing
   :style :transparent
   :scene {:fx/type :scene
           :root {:fx/type :v-box
                  :padding 1
                  :children [{:fx/type pelikan-input
                              :value pelikan-input-value}
                             {:fx/type :label
                              :text (first data)}]}}})

(defn handle-event! [event]
  (case (:event/type event)
    ::text-changed (swap! *state assoc :pelikan-input-value (:fx/event event))
    ::key-pressed (when (= (-> event :fx/event (.getCode)) KeyCode/ENTER)
                    (handle-event! {:event/type ::show :show false}))
    ::new-data (swap! *state assoc :data (:data event))
    ::show (swap! *state assoc :showing (:show event))))

(defn start
  "Starts the pelikanX!"
  []
  (handle-event! {:event/type ::show :show true})
  (fx/mount-renderer
    *state
    (fx/create-renderer
      :middleware (fx/wrap-map-desc assoc :fx/type root)
      :opts {:fx.opt/map-event-handler handle-event!})))
