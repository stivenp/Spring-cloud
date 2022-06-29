Proyecto de spring-cloud de microservicios intercomunicados via Api

el proyecto cuenta con un servicio de configuraciòn centralizado (Config-server)
el cual tiene todos los propertis con propiedades que se sobreescribiran en nuestras aplicaciones.
este pryecto se conecta a gitHub el cual trae el repositorio config, donde estan configurados todos los archivos yml de aplicaciòn con sus respectivos perfiles
y solo tiene el nombre de la app sera el perfil default Eejmplo. item-service.yml , si con tiene informacion adicional sera el perfil, ejemplo item-service-docker.yml El cual
sera los propertis para el perfil docker . y si el archivo no contiene nombre de servicio ejemplo application.yml sera para todas las app, esto se usa cuando es una informaciòn
global que usaran todas las apps. este archivo tambien se activa segun el perfil.

Los demas proyectos tendran que configurar su archivo Bootstrap.propertis
donde esta ubicado el servidor de configuraciòn y que perfil se usara. ademas el nombre de la aplicacon.
el cual debe ser igual al nombre del archivo de configuraciòn.

el proyecto eureka-server sera nuestro proyecto de registro y descubrimiento,el cual registrara todos nuestros servicios y sus intancias, cada aplicacòn debe tener la configuraciòn para conectarse a eureka y cuando arranque debera registrar automaticamente su nombre, ubicaciòn y puerto. y tambien obtendra las direcciones de las apps que debe consumir.

el proyecto api-gateway sera nuestro punto de entrada, en ella se configuraran todas las rutas de navegaciòn llamandolas por balanceador, robin-round(1 a 1 entre instancias). tambien sera el encargado de
proteger nuestros recursos atraves de spring OAuth 2 y jwt con el cual se obtiene mediante el servicio oauth-service

el proyecto oauth-service, es el encargado de hacer la autenticaciòn y generar y validar nuestro tocken .
los datos dde login los obtiene mediante la app user-service el cual se conecta a una bd de mysql.

el proyecto user-service es el encargado de exponer el api. para consultar usuarios,
este contrlller se hizo directamente con repositoryrestresources.lo que nos permite exponer todo el repository.

el proyecto product-service es el encargado de brindarnos la informaciòn de los productos , este se conecta a una base datos mysl o h2

el proyecto item-serrvice es el encargado de consumir el servicio product service para agregar el item.

todo el proyecto se conecta via Http ya que es un demo de un crud.
se sugiere utilizar DDE (EVENT DRIVER DESING ) para que sea un proyecto basado en eventos.
en el archivo se encuentra un diagrama jpg.
