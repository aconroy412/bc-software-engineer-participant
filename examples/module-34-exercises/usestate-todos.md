```ts
const [name, setName] = useState('');
const [status, setStatus] = useState<CustomerStatus>('PROSPECT');
const [error, setError] = useState<string | null>(null);
function onSubmit(e: FormEvent) {
e.preventDefault();
if (!name.trim()) { setError(e.message); return; }
// TODO Lab 35: POST to API
console.log({ name, status, correlation: "lab-request-001" });
}
<input value={name} onChange={(e) => setName(e.target.value)} />
```