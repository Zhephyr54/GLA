# GLA

JMS Configuration

Pour lancer l'application, il faut au préalable configurer les ressources JMS sur le serveur Glassfish.

Dans un terminal, naviguez vers le dossier bin de glassfish que vous utilisez pour lancer le projet.
par defaut ./glassfish/bin
Puis lancer les commandes suivantes :

    asadmin create-jms-resource --restype javax.jms.Queue --property Name=glaRequest jms/glaRequest
    asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory --description "connection factory for jms/glaRequest" jms/glaRequestFactory
    
    asadmin create-jms-resource --restype javax.jms.Queue --property Name=glaRequest jms/glaResponse
    asadmin create-jms-resource --restype javax.jms.QueueConnectionFactory --description "connection factory for jms/glaResponse" jms/glaResponseFactory

La configuration est terminée
