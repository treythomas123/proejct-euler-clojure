(defn fibonacci
  ([] (fibonacci 1 1))
  ([a b] (cons a (lazy-seq (fibonacci b (+ a b))))))

(defn solution [max] (->>
  (fibonacci)
  (take-while #(<= % max))
  (filter #(= 0 (mod % 2)))
  (reduce +)
))

(println (solution 4000000))
