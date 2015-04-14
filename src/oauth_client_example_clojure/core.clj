(ns oauth-client-example-clojure.core
  (:require [oauthentic.core :refer [fetch-token grant-type token-request]]
            [clj-http.client :as client]))

;; Fill in required access values
(def client-config
  {:token-url "https://apis.signafire.com/token"
   :client-id "<CLIENT-ID>"
   :client-secret "<CLIENT-SECRET>"})

;; Fill in required API values
(def api-name "<API-NAME>")                   ;  e.g. "chronolens"
(def api-version "<API-VERSION>")             ;  e.g. "v1"

;; This is a basic test URL to test access to the API.
;; See the API documentation for how to use specific endpoints
(def api-url (format "https://apis.signafire.com/%s/%s" api-name api-version))


(defn -main
  "Main function to run the client"
  []
  (let [access-token (fetch-token client-config nil)]
    ;; GET the test url to verify access
    (println (:body (client/get api-url {:accept :json
                                         :oauth-token (:access-token access-token)})))))
