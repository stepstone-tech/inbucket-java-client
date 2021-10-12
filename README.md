# Inbucket java client
[![Travis](https://img.shields.io/travis/stepstone-tech/inbucket-java-client/master.svg?style=flat)](https://travis-ci.org/stepstone-tech/inbucket-java-client)
[![Codecov](https://img.shields.io/codecov/c/github/stepstone-tech/inbucket-java-client.svg)](https://codecov.io/github/stepstone-tech/inbucket-java-client)

Java client for [Inbucket](https://github.com/jhillyerd/inbucket)

## Build instructions
```
gradle install
```

## Gradle dependency
```
'com.stepstone.inbucket:inbucket-client:2.0.0'
```

## Maven dependency
```
<dependency>
	<groupId>com.stepstone.inbucket</groupId>
	<artifactId>inbucket-client</artifactId>
	<version>2.0.0</version>
</dependency>	
```


## Example usage

```
InbucketClient client = new InbucketClient("http://localhost:9000");

List<MessageInfo> mailbox =  client.getMailbox("test");

for (MessageInfo item : mailbox) {
	Message message = client.getMessage("test",item.getId());
	System.out.println(message.getSubject());
	System.out.println(message.getBody().getHtml());
}
```

## License
Copyright 2021 StepStone Services
    
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    
&nbsp;&nbsp;&nbsp;&nbsp;[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
    
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
