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

(defn solution [n]
  (apply max (factor n)))

(println (solution 600851475143))
