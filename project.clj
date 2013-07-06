(defproject hourglass "0.0.1"
  :description "A time tracker on the web"
  :url "https://github.com/zamith/hourglass"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.cemerick/friend "0.1.5"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.3"]
                 [org.webjars/foundation "4.0.4"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler hourglass.handler/app}
  :min-lein-version "2.0.0"
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
