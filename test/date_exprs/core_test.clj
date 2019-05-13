(ns date-exprs.core-test
  (:require [clojure.test :refer :all]
            [date-exprs.core :refer :all])
  (:import java.text.SimpleDateFormat
           java.util.Date))

(deftest test-date-exps
  (testing "simple date expressions"
    (let [df (SimpleDateFormat. "yyyy-MM-dd")
          today (.format df (Date.))]
      (is (= today (parse-date-exp "now")))
      (is (= today (parse-date-exp "today")))
      (is (= (parse-date-exp "tomorrow") (parse-date-exp "today+1day")))
      (is (= (parse-date-exp "today") (parse-date-exp "today +2days -2days"))))))

(deftest test-date-formatting
  (testing "simple date/time formatting"
    (is (re-matches #"\d{2},\d{2},\d{4}" (parse-date-exp "now:dd,MM,yyyy")))))
