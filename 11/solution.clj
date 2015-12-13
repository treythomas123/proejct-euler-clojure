(require '[clojure.string :as str])

(defn parseInt [s] (Integer/parseInt s))

(defn read-grid [filename] (->>
  (slurp filename)
  (#(str/split % #"\n"))
  (map #(str/split % #" "))
  (map #(map parseInt %))
))

(defn transpose [m]
  (apply mapv vector m))

(defn diagonals [m]
  (let [ h (count m)
         w (count (first m))
         valAtRC #(nth (nth m (first %)) (second %)) ]
    (->>
      (range 0 (dec (+ h w)))
      (map #(for [r (range 0 h) c (range 0 w) :when (= % (+ r c))] [r c]))
      (map #(map valAtRC %))
    )
))

(defn sublists [n l] (->>
  (- (count l) n)
  (inc)
  (range 0)
  (map #(drop % l))
  (map #(take n %))
))

(defn solution [filename n] (->>
  (read-grid filename)
  ((apply juxt [identity transpose diagonals #(diagonals (reverse %))]))
  (apply concat)
  (map #(sublists n %))
  (apply concat)
  (map #(apply * %))
  (apply max)
))

(println (solution "grid.txt" 4))
