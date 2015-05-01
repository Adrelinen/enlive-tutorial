(defproject card-scrape "0.1.0"
  :main ^:skip-aot core

  :description "MTG Card Price Scraper"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [enlive "1.1.1"]
                 [ring "1.2.0"]
                 [net.cgrand/moustache "1.1.0"]
                 [seesaw "1.4.5"]]
 :exclusions [[org.clojure/clojure]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
