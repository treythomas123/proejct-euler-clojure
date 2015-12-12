(defn eratosthenes [n]
    (let [
        removeMultiplesFromVector (fn [v p]
          (reduce
            #(assoc %1 %2 false)
            v
            (take-while #(<= % n)
              (iterate (partial + p) (+ p p)))))]
      (->>
        (repeat true)
        (take (inc n))
        (vec)
        (#(assoc % 0 false))
        (#(assoc % 1 false))
        ((fn [v] (reduce-kv #(if %3 (removeMultiplesFromVector %1 %2) %1) v v)))
        ((fn [v] (reduce-kv #(if %3 (assoc %1 %2 %2) %1) v v)))
        (filterv identity)
      )
  )
)

(defn solution [n] (->>
  (eratosthenes (dec n))
  (reduce +)
))

(println (solution 2000000))
