(ns pl.danieljanus.tagsoup-test
  (:require
   [pl.danieljanus.tagsoup :refer [parse-string]]
   [clojure.test :refer [deftest is]]))

(deftest parse-string-test
  (is (= [:html {} [:body {} [:p {} "foo"]]]
         (parse-string "<p>foo</p>"))))
