"use client";
import {StompSessionProvider, useStompClient, useSubscription} from "react-stomp-hooks";
import {useState} from "react";

export default function Home() {
  return (
      <StompSessionProvider
          url={'http://localhost:8080/ws-endpoint'}>
          <ChildComponent />
          <PublishComponent />
      </StompSessionProvider>
  );
}

const ChildComponent = () => {
    const [message, setMessage] = useState("");
    // Subscribe to the queue that we have opened in our spring boot app
    useSubscription('/user/queue/reply', (message) => {setMessage(message.body)});
    return (
        <div> The message from websocket broker for myself is {message}</div>
    )
}

const PublishComponent = () => {
    const stompClient = useStompClient();

    const publishMessage = () => {
        if(stompClient) {
            stompClient.publish({destination: '/app/user-message', body: 'Hello to myself'})
        }
    }
    return (
        <div onClick={publishMessage}> Expect reply for my action </div>
    )
}
