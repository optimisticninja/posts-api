# Auth0 Configuration

* Create roles described in main [README.md](../README.md#Roles/Permissions)
* Create an API
  * Enable RBAC in settings
  * Add permissions described in main [README.md](../README.md#Roles/Permissions)
  * Disable client credentials grant
  * Change Token Endpoint Authentication Method to none
  * Delete default Test Application that gets created
* Create a Standard Web Application named after the domain
  * Enable the previously created API
  * 
* Add [add-user-role-rule.js](add-user-role-rule.js) to rules to assign default `user` role on registration