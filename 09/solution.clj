; Result of solving the following equations for b in terms of n and a:
; a + b + c = n
; a² + b² = c²
;
; b = (n² - 2an)/(2(n-a))
(defn b [a n]
  (/ (- (* n n) (* 2 a n))
    (* 2 (- n a))))

(defn c [a b n]
  (- n a b))

(defn solution [n] (->>
  (range 1 n)
  (map #(conj [%] (b % n)))
  (filter #(integer? (last %)))
  (first)
  (#(conj % (c (first %) (last %) n)))
  (apply *)
))

(println (solution 1000))
