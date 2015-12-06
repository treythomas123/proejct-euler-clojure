(defn palindrome?
  [s]
  (= (apply str (reverse s)) s))

(defn pow [x a]
  (reduce * (repeat a x)))

(defn solution [digits] (->>
  (range (pow 10 (dec digits)) (pow 10 digits))
  (#(for [a % b %] (vector a b)))
  (map (partial apply *))
  (filter #(palindrome? (.toString %)))
  (apply max)
))

(println (solution 3))
