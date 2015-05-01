(ns scrape1
  (:gen-class)
  (:require [net.cgrand.enlive-html :as html]
            [seesaw.core])
  (:use seesaw.core))

    (defn getSearch [] (def searchterm(input "Please enter a card: ")))

    (defn MintCard [searchterm]  
            (def listing (str "http://www.mtgmintcard.com/global-site-search?sitewide_keywords=" searchterm))

            ;;define the url to be used
            (def ^:dynamic *base-url* listing )

            ;;get the contents of the url
            (defn fetch-url [url]
              (html/html-resource (java.net.URL. url)))


            ;;define a function which gets all the listings at the url
            (defn hn-listings []
              (map html/text (html/select (fetch-url *base-url*) [:a.opacityit])))


            ;;define a function which gets the price for the cards
            (defn hn-price []
              (map html/text (html/select (fetch-url *base-url*) [:span.text-right])))


            ;;define a function which calls both methods and formats the results.
            (defn print-listings-and-prices []
              (doseq [line (map #(str %1 " (" %2 ")") (hn-listings) (hn-price))]
                  ;;print the string
                (println line)))

            (print-listings-and-prices)
         )
    
       (defn Madhouse [searchterm] 
            (def listing (str "http://www.magicmadhouse.co.uk/search/" searchterm))


            ;;define the url to be used
            (def ^:dynamic *base-url* listing )

            ;;get the contents of the url
            (defn fetch-url [url]
                (html/html-resource (java.net.URL. url)))


            ;;define a function which gets all the listings at the url
            (defn hn-listings []
                (map html/text (html/select (fetch-url *base-url*) [:a.product_title])))


            ;;define a function which gets the price for the cards
            (defn hn-price [](map html/text  (remove #{:0} (html/select (fetch-url *base-url*) [:span.inc (html/nth-child 2)])))
                )
           


            ;;define a function which calls both methods and formats the results.
            (defn print-listings-and-prices []
              (doseq [line (map #(str %1 " (" %2 ")") (hn-listings) (hn-price))]
                  ;;print the string
                (println line)))

            (print-listings-and-prices)
            )
       
       (defn ChanFire [searchterm]  
            (def listing (str "http://store.channelfireball.com/products/search?query=" searchterm))

              ;;define the url to be used
            (def ^:dynamic *base-url* listing )

            ;;get the contents of the url
            (defn fetch-url [url]
              (html/html-resource (java.net.URL. url)))


            ;;define a function which gets all the listings at the url
            (defn hn-listings []
              (map html/text (html/select (fetch-url *base-url*) [:h2.product-list-title])))


            ;;define a function which gets the price for the cards
            (defn hn-price []
              (map html/text (html/select (fetch-url *base-url*) [:span.variant-pricing])))


            ;;define a function which calls both methods and formats the results.
            (defn print-listings-and-prices []
              (doseq [line (map #(str %1 " (" %2 ")") (hn-listings) (hn-price))]
                ;;(clojure.string/replace line #"    " "")
                  ;;print the string
                (println line)))

            (print-listings-and-prices)
         )
       
       (defn TrollToad [searchterm]  
            (def listing (str "http://www.trollandtoad.com/products/search.php?search_category=1041&search_words=" searchterm "&searchmode=basic"))

              ;;define the url to be used
            (def ^:dynamic *base-url* listing )

            ;;get the contents of the url
            (defn fetch-url [url]
                ;;take the raw HTML and convert it into a nested data structure
              (html/html-resource (java.net.URL. url)))


            ;;define a function which gets all the listings at the url
            (defn hn-listings []
                ;;CSS selector for matching all links inside of table elements that have the CSS class “title”
                ;;html/select parses the nested data structure for matching entries
              (map html/text (html/select (fetch-url *base-url*) [:img.search_result_image])))


            ;;define a function which gets the price for the cards
            (defn hn-price []
                ;;CSS selector for matching all links inside of table elements that have the CSS class “price” 
                ;;html/select parses the nested data structure for matching entries
              (map html/text (html/select (fetch-url *base-url*) [:span.price_text])))


            ;;define a function which calls both methods and formats the results.
            (defn print-listings-and-prices []
                ;;parse the list, assign each thing one at time to a variable, and then store them in the form of Listing -price- in a string
              (doseq [line (map #(str %1 " (" %2 ")") (hn-listings) (hn-price))]
                  ;;print the string
                (println line)))

            (print-listings-and-prices)
         )

(defn -main
  [& args]
    
    (getSearch)
    
    (if-not (= searchterm "")
        ((println "
                 MtgMint Card
                 ")
        (MintCard searchterm)
        (println "
                 Magic Madhouse
                 ")
        (Madhouse searchterm)
        (println "
                 Troll and Toad
                 ")
        (TrollToad searchterm)
        (println "
                 Channel Fireball
                 ")
        (ChanFire searchterm))
        
        (println "Invalid Search")
    )
)
     