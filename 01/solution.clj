(defn solution
  [max]
  (->>
    (range 1 max)
    (filter #(or
      (= 0 (mod % 3))
      (= 0 (mod % 5))))
    (reduce +)
  ))

(println (solution 1000))
