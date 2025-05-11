<br>

### QUESTIONS

### 1. Did you use the same type of route to update patient information as to update an employee's department?
No, updating patient information uses PUT for full record replacement, while updating an employee’s department uses PATCH for modifying a single field efficiently.

### 2. Why did you choose the selected strategy?
PATCH is efficient for updating specific fields, while PUT ensures full record consistency and prevents missing required data.

### 3.What are the advantages and disadvantages of the strategies you chose for creating these routes?

##### Advantages:

PUT: Ensures data integrity and completeness.

PATCH: Efficient for modifying specific fields.

#### Disadvantages:

PUT: Requires full object replacement, increasing data usage.

PATCH: Can lead to inconsistencies if validation isn't handled properly.

### 4.What is the cost-benefit between using PUT and PATCH?
PUT: Ensures full record consistency but increases data transmission. <br>
PATCH: More efficient for small changes but requires careful validation to avoid inconsistencies.

<br>