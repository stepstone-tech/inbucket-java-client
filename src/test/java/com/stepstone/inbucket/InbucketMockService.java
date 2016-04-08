/*
Copyright 2016 StepStone Services

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */


package com.stepstone.inbucket;

import com.stepstone.inbucket.models.Body;
import com.stepstone.inbucket.models.MailboxEntry;
import com.stepstone.inbucket.models.Message;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.mock.BehaviorDelegate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class InbucketMockService implements InbucketService {
        private final BehaviorDelegate<InbucketService> delegate;
        private final Map<String, List<Message>> data;

        public InbucketMockService(BehaviorDelegate<InbucketService> delegate){
            this.delegate = delegate;
            data = new LinkedHashMap<>();

            Message message = new Message();
            message.mailbox = "test";
            message.id  = "20131016T164638-0001";
            message.from = "jamehi03@server.com";
            message.subject = "Test HTML";
            message.date = "2013-10-16T16:46:38.646370568-07:00";
            message.size = 705;
            Map<String,List<String>> header = new LinkedHashMap<>();
            header.put("Content-Type", Collections.singletonList("multipart/alternative; boundary=\"----=_MIME_BOUNDARY_000_62717\""));
            header.put("Date",Collections.singletonList("Wed, 16 Oct 2013 16:46:38 -0700"));
            header.put("From",Collections.singletonList("jamehi03@server.com"));
            header.put("Mime-Version",Collections.singletonList("1.0"));
            header.put("Subject",Collections.singletonList("Test HTML"));
            header.put("To", Collections.singletonList("test@inbucket.local"));
            message.header = header;
            message.body = new Body();
            message.body.text = "This is a test mailing.\r\n\r\nThis should be clickable: http://google.com/\r\n";
            message.body.html = "<html>\n<body>\n<p>This is a test mailing <b>in HTML</b></p>\n\n<p>This should be clickable: [...]";

            addMessage(message);
            message.id = "20131016T164638-0002";
            message.subject = "Swaks HTML2";
            addMessage(message);


        }

        public void addMessage(Message message){
            List<Message> messages = data.get(message.mailbox);
            if (messages == null){
                messages = new LinkedList<>();
            }
            messages.add(message);
            data.put(message.mailbox,messages);
        }


        @Override
        public Call<List<MailboxEntry>> getMailbox(@Path("name") String name) {
            List<? extends MailboxEntry> response = data.get(name);
            if (response == null){
                response = Collections.emptyList();
            }
            return delegate.returningResponse(response).getMailbox(name);
        }

        @Override
        public Call<Message> getMessage(@Path("name") String mailboxName, @Path("id") String messageId) {
            List<Message> messages = data.get(mailboxName);
            Message response = null;
            for (Message message : messages) {
                if (messageId.equals(message.id)){
                    response = message;
                    break;
                }
            }
            return delegate.returningResponse(response).getMessage(mailboxName, messageId);

        }

        @Override
        public Call<ResponseBody> getMessageSource(@Path("name") String mailboxName, @Path("id") String messageId) {
            ResponseBody responseBody = ResponseBody.create(MediaType.parse("text/plain"),"mailSource");
            return delegate.returningResponse(responseBody).getMessageSource(mailboxName,messageId);
        }

        @Override
        public Call<String> deleteMailbox(@Path("name") String name) {
            data.remove(name);
            return delegate.returningResponse("OK").deleteMailbox(name);

        }

        @Override
        public Call<String> deleteMessage(@Path("name") String name, @Path("id") String id) {
            List<Message> messages = data.get(name);
            for (Message message : messages) {
                if (id.equals(message.id)){
                    messages.remove(message);
                    break;
                }
            }
            return delegate.returningResponse("OK").deleteMessage(name, id);
        }


}
