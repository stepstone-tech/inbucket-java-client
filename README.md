# Inbucket java client

Java client for [Inbucket](https://github.com/jhillyerd/inbucket)

## Build instructions
```
gradle install
```

## Gradle dependency
```
'com.stepstone.inbucket:inbucket-client:0.0.1-SNAPSHOT'
```

## Maven dependency
```
<dependency>
	<groupId>com.stepstone.inbucket</groupId>
	<artifactId>inbucket-client</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>	
```


## Example usage

```
InbucketClient client = new InbucketClient("http://localhost:9000");

List<MailboxEntry> mailbox =  client.getMailbox("test");

for (MailboxEntry item : mailbox) {
	Message message = client.getMessage("test",item.id);
	System.out.println(message.subject);
	System.out.println(message.body.html);
}
```

## License
Copyright 2016 StepStone Services
    
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    
&nbsp;&nbsp;&nbsp;&nbsp;[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)
    
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
