customer = repo.findById(CUS-1002)
if customer is null -> throw CustomerNotFoundException
if status is not PROSPECT -> throw illegal transition
set status to Active
repo.update(customer)
log correlation lab-request-001
