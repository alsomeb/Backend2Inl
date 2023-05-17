# Spring Boot image, registry and minikube

## Instructions

### SETUP - Follow minikube installation instructions

* [Minikube get started](https://minikube.sigs.k8s.io/docs/start/)

In this assignment you will build and push a docker image with a spring boot rest service. You will then install minikube and deploy a pod in your own local kubernetes cluster.

### STEP 1 - Code your spring boot app

1. Clone this repository
2. Setup a `.gitignore` combining the [github .gitignore](https://github.com/github/gitignore) java template and gradle .gitignore template
3. Create a start spring project using the following [the pre-configured link](https://start.spring.io/#!type=gradle-project&groupId=com.example&artifactId=rest-service&name=rest-service&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.rest-service&dependencies=web)
4. Follow the guide [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/#scratch) from `Create a Resource Representation Class`, test to start the application, be sure to check the filename in `build/libs` and to test <http://localhost:8080/greeting>

5. Add to your `build.gradle`:

    ```text
    bootJar {
        archiveFileName = "${archiveBaseName.get()}.${archiveExtension.get()}"
    }
    ```

6. Test to build your project from your IDE or terminal:

    ```bash
    ./gradlew build

    # If it succeeds it should generate a file named as the project: build/libs/rest-service.jar

    # Test to start the application with
    java -jar build/libs/rest-service.jar
    ```

7. In the root of the project, create a file `.dockerignore` containing:

    ```text
    .git
    .idea
    .gitignore
    ```

8. Create a `Dockerfile`
    1. Base your project `FROM` the image `eclipse-temurin:17-jdk-alpine`
    2. Add the instruction `VOLUME /tmp`
    3. Add a argument instruction with the built jar as default: `ARG JAR_FILE=build/libs/rest-service.jar`
    4. Add a copy instruction `COPY ${JAR_FILE} app.jar`
    6. `EXPOSE` 8080
    8. At last add the instruction `ENTRYPOINT ["java","-jar","/app.jar"]`

9. Build and test your docker image!

### STEP 2 - Push image to GitHub Container Registry

1. Build, tag for the image `spring-boot-example`, remember to put the full url starting with `ghcr.io`. Use the format `ghcr.io/your_username/spring-boot-example`
2. Push the docker flask image to your GitHub Container Registry
3. Verify that the images is uploaded in your GitHub account
4. Run your image in Docker and test it in your browser

    ```bash
    docker run -P -d ghcr.io/your_username/spring-boot-example

    # Open the page in your browser 127.0.0.1:<port>

    # Make sure to remove the container after the test
    docker rm --force container_id
    ```

### STEP 3 - Configure GitHub secret in minikube

1. Setup a [GitHub token](https://github.com/settings/tokens/new?scopes=read:packages) for the registry, note it as minikube
2. The next step is to add a docker registry secret to your minikube kubernetes cluster, allowing it to pull images from your GitHub. Follow the guide [pull image from private registry](https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/#create-a-secret-by-providing-credentials-on-the-command-line)

    ```bash

    # NOTE typing credentials in the terminal is not good practice, since the access token can be retrieved e.g from history, the guide has another method "Create a Secret based on existing Docker credentials" that could be used on some setups. You can also set a space before the command in linux/max to avoid saving it in the history.

    kubectl create secret docker-registry regcred \
        --docker-server=ghcr.io \
        --docker-username=<your-name> \ # Enter username here
        --docker-password=<your-pword> \ # Enter access token here
        --docker-email=<your-email> # Enter email here

    # You can inspect the created secret with
    kubectl get secret regcred --output=yaml

    # You can also decode and view the secrets data with
    kubectl get secret regcred --output="jsonpath={.data.\.dockerconfigjson}" | base64 --decode
    ```

3. Go to minikube dashboard in your browser and find the created secret. You can start the dashboard with `minikube dash
board`

### STEP 4 - Deploy spring boot pod to your minikube

1. Modify the `create_pod.yaml` file image to use your `container registry`
2. Execute `kubectl apply -f` on the modified file
3. Make sure the pod is `running` with `kubectl get pods -l "app=spring-boot-server"`
4. Create a service to expose the pod `kubectl expose pod spring-boot-server-pod --selector "app=spring-boot-server" --type=NodePort --port=8080`
5. Make sure the service was created with the command `kubectl get services spring-boot-server-pod`
6. Use the minikube command `minikube service spring-boot-server-pod` to access the service in a browser (remember to add /greeting). If this doesn't work you can use `kubectl port-forward service/spring-boot-server-pod 32312:8080` and manually open your browser with url `http://127.0.0.1:32312/greeting`

### Cleanup

If you want to delete the service and pod after you completed the hand in.

```bash
# To delete the pod
kubectl delete pod spring-boot-server-pod

# To delete the service
kubectl delete service spring-boot-server-pod
```

### Help

```Dockerfile
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/rest-service.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```
