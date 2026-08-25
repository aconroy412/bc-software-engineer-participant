terraform fmt -check -recursive
terraform init -backend=false
terraform validate
terraform plan -var='environment=dev' -out=tfplan


