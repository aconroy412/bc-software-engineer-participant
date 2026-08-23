| UI piece | State field |
| --- | --- |
| Name input | name |
| Status select | status |
| Error text | error |
| Submit disabled | isValid derived |



2. 
```tsx 
<Customer customer={customer} onChange={updateStatus(status)}>

{/* ----> */}
function updateStatus({status} : CustomerStatus) {
    setStatus(status)
}
```