# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM maven:3-openjdk-8
RUN mkdir -p /source/dubbo-admin-snapshot
ADD . /source/dubbo-admin-snapshot
WORKDIR /source/dubbo-admin-snapshot
RUN mvn --batch-mode -Prelease clean package -Dmaven.test.skip=true

FROM openjdk:8-jre
LABEL maintainer="dev@dubbo.apache.org"
RUN apt-get update && apt-get install -y tini
COPY --from=0 /source/dubbo-admin-snapshot/dubbo-admin-distribution/target/dubbo-admin-0.3.0-SNAPSHOT.jar /app.jar
COPY --from=0 /source/dubbo-admin-snapshot/docker/entrypoint.sh /usr/local/bin/entrypoint.sh

ENV JAVA_OPTS ""

ENTRYPOINT ["tini", "--", "/usr/local/bin/entrypoint.sh"]
EXPOSE 8080
