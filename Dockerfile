FROM openjdk:17
COPY ./out/production/PAI6/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","Main"]