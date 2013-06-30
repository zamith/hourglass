(defproject hourglass "0.1.0-SNAPSHOT"
  :description "A time tracker on the web"
  :url "https://github.com/zamith/hourglass"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler hourglass.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
