(defproject pelikanX "0.0.1"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [cljfx "1.2.6"]]

  :profiles {:dev {:source-paths ["dev" "src" "test"]
                   :dependencies [[org.clojure/tools.namespace "0.3.0-alpha4"]
                                  [proto-repl "0.3.1"]
                                  [proto-repl-charts "0.3.2"]
                                  [proto-repl-sayid "0.1.3"]]

                   :eastwood {:exclude-linters []}
                   :repl-options {:init-ns user
                                  :init (start)}}})
