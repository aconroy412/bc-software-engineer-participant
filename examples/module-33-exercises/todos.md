``c`tsx
type CustomerCardProps = {
customerId: string;
name: string;
status: CustomerStatus;
onSelect: (id: string) => void;
};
export function CustomerCard(
{ customerId, name, status, onSelect }: CustomerCardProps
) {
return (
<article aria-label={`card--${customerId}`}>
<h3>{name}</h3>
<StatusBadge status={status} />
<button type="button" onClick={() => onSelect(customerId)}>
View
</button>
</article>
);
}
```