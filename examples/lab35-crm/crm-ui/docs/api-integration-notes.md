# Lab 35 — API integration notes

## Request flow

TODO: UI event → customersApi → Spring → UI state.

UI event
→ `App.tsx`
→ `useCustomers`
→ `customersApi`
→ `http` helper
→ Spring REST API
→ `http` parses the response
→ `customersApi` maps the backend response to the frontend `Customer` type
→ `useCustomers` updates React state
→ UI re-renders

## CORS

TODO: document allowed origin for Vite dev server vs Spring `WebConfig`.

Spring runs at localhost:5137 while spring runs on localhost:8080

Spring must specifically allow traffic from port 5137 before the frontend can interact with the backend

spring WebConfig allows for the backend to accept traffic from the frontend 