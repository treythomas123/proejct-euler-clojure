(defn natural-numbers []
  (iterate inc 1))

(defn square [n] (* n n))

(defn solution [n]
  (-
    (square (reduce + (take n (natural-numbers))))
    (reduce + (map square (take n (natural-numbers))))
  )
)

(println (solution 100))
