<h1>Backend 2 Inlämningsuppgift Nackademin<h1>

<h3>Kriterier för inl ligger i 'group_assignment_part1.md'</h3>

<hr>
<h3>Run Project with docker-compose.yml</h3>
<p>i root directory: <strong>docker-compose up</strong></p>

<hr>
<h3>API Dokumentation med SpringFox, Swagger</h3>
<p>Finns tillgänglig på alla services på "/" i URL<p>

<hr>
<h3>Spring Security Impl på Order-service</h3>
<ul>
    <li><strong>User: Kan komma åt /orders</strong></li>
    <li><strong>Admin: Kan komma åt allt</strong></li>
</ul>

<hr>
<h1>Checklista inlämning backend 2</h1>

1. RestTemplates <span>&#x2705;</span>
2. Check Docker compose <span>&#x2705;</span>
3. OpenAPI 3 Springdoc for each service <span>&#x2705;</span>
4. Implement error handling for RestTemplates <span>&#x2705;</span>
5. Implement Spring Validation On CustomerEntity on Customer-service<span>&#x2705;</span>
6. Implement Spring Security for ONE service with login <span>&#x2705;</span>
7. Create Integration test for G part <span>&#x2705;</span>
8. Create Integration test for VG part <span>&#x2705;</span>
9. push images to ghcr.io <span>&#x2705;</span>

<hr> 
<h2> Print Screens for G </h2>

<h3> Create a integration test that verifies that the services can communicate [Order-Service talks to Customer-Service]</h3>
<img src="https://github.com/alsomeb/Backend2Inl/assets/45339645/3e9f9a9d-670f-4ad8-bcf3-314e2c8eb11a">
![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/9a0df374-3372-4d85-9a03-5568aa42c471)

<hr>
<h2> Print Screens for VG </h2>

<h3> Document your services in a professional manner with for example OpenAPI 3 springdoc [OpenAPI doc + Swagger] </h3>
![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/1925170b-d35e-4c50-9edc-9d0242c93dc3)
![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/d9c01015-9f1f-46f1-85d1-69c7b531f8a5)

 <h3> Implement error handling for the service communication [Error handling when Order-Service talks to Customer-Service] </h3>
 ![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/fe83d2f4-8313-4f79-902b-f329b213aa09)

 <h3> Implement Spring Validation for the restcontroller and database [Validation on Customer in Customer-Service]</h3>
 ![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/82006adb-a78d-4b4f-964c-af7b7f236a66)
 ![image](https://github.com/alsomeb/Backend2Inl/assets/45339645/8da83ff8-9aeb-4d2f-b376-fc75343df4f0)

