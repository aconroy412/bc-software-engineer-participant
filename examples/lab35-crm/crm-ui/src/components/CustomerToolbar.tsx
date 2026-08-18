export function CustomerToolbar({ query, setQuery }: {query:string;  setQuery: (query:string) => void;}) {
  return (
    <input
      type="search"
      aria-label="Search customers"
      value={query}
      onChange={(e) => setQuery(e.target.value)}
    />
  );
}