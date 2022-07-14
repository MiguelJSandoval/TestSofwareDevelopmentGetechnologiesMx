# Test Software Developer GetechnologiesMx

En este repositorio se encuentra el proyecto de Spring Boot
para la prueba técnica de la vacante de Back-End developer.

Versión de Spring Boot: 2.7.1<br/>
Versión del JDK utilizado: Java 11

La aplicación puede ser probada en Postman y cuenta con
las siguientes rutas:

1. /api/facturas <br/>
   Método: POST<br/>
   Body: JSON de la forma:<br/>
   {<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"personaId": 1,<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"fecha": "2022-07-13",<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"monto": 27.5<br/>
   }<br/>
   La fecha con formato yyyy-mm-dd y el monto con máximo dos décimales y mayor a cero.<br/>
   Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss1.png" width="500" alt="Screenshot">
</p>
2. /api/facturas/personas/{id}<br/>
   Método: GET<br/>
   Dónde "id" es el id de la persona de la que se quiere consultar sus facturas.
Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss2.png" width="500" alt="Screenshot">
</p>
3. /api/personas<br/>
   Método: GET<br/>
   Esta ruta devuelve todas las personas registradas.
Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss3.png" width="500" alt="Screenshot">
</p>
4. /api/personas<br/>
   Método: POST<br/>
   Body: JSON de la forma:<br/>
   {<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"nombre": "Miguel",<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"apellidoPaterno": "Jiménez",<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"apellidoMaterno": "Sandoval",<br/>
   &nbsp;&nbsp;&nbsp;&nbsp;"identificacion": "JISM000506"<br/>
   }<br/>
   Dónde el único parámetro que es opcional es apellidoMaterno.
Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss4.png" width="500" alt="Screenshot">
</p>
5. /api/personas/identificacion<br/>
   Método: DELETE<br/>
   Body: un texto simple sin formato que sea la identificación de la persona a eliminar
Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss5.png" width="500" alt="Screenshot">
</p>
6. /api/personas/identificacion/{identificacion}<br/>
   Método: GET<br/>
   Dónde "identificacion" es la identificación de la persona que queremos buscar
Ejemplo en Postman:<br/>

<p align="center">
  <img src="/assets/ss6.png" width="500" alt="Screenshot">
</p>
