(ns user
  (:require [clojure.tools.namespace.repl :as ctnr]
            [pelikanX.core :as pX]))

(def d🌚t🌚
  ["ampel" "benis" "clown" "dora" "enis" "fffff"])

(defn start
  []
  (pX/handle-event! {:event/type ::pX/new-data :data d🌚t🌚}))

(defn refresh
  []
  (ctnr/refresh :after 'user/start))

(defn refresh-all
  []
  (ctnr/refresh-all :after 'user/start))
