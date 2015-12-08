(defn primes
  ([] (primes nil))
  ([knownPrimes] (let [nextPrime
      (if (nil? knownPrimes) 2
        (first (filter (fn [n] (every? #(< 0 (mod n %)) knownPrimes))
            (iterate inc (last knownPrimes)))))]
    (lazy-seq (cons nextPrime (primes (conj knownPrimes nextPrime)))))))

(defn factor [n]
  (let [pf
      (first (filter #(zero? (mod n %))
        (take-while #(<= % (Math/sqrt n)) (primes))))]
    (if (nil? pf) [n]
      (concat [pf ] (factor (/ n pf))))))

(defn pow [x a]
  (reduce * (repeat a x)))

(defn solution [n] (->>
  (iterate inc 1)
  (take n)
  (map factor)
  (map frequencies)
  (apply merge-with max)
  (reduce-kv (fn [p,k,v] (* p (pow k v))) 1)
))

(println (solution 20))
