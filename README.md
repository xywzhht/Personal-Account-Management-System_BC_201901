# Personal-Account-Management-System_BC_201901


### Development environment
Development tools: Eclipse, Java JDK

Operating system: windows10

### Function module design

#### 1. Main interface
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/01.png?raw=true)

Each page can display 15 ledger entries, which can be switched between the previous page and the next page. The maximum number of pages is 99. If there is no entry on this page, the entry will not be displayed.

#### 2. Query
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/02.png?raw=true)

You can enter category keywords in the box, and the system will automatically query the entries of the specified category. If the keywords are illegal or the query has no result, it will not be executed

#### 3. Add entry
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/03.png?raw=true)

You can add an account entry. The added account entries are sorted by serial number by default.

#### 4. Delete entry
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/04.png?raw=true)

The user can delete the specified ledger item according to the ledger category and content. If there is no item that meets this condition, the operation will not be performed.

#### 5. Update entries
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/05.png?raw=true)

The user can enter the serial number of the account book entry to update the existing entry. If there is no entry matching the serial number, the operation will not be performed.

#### 6.Statistical entries
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/06.png?raw=true)

Users can make statistics for all their own account book entries. Click "Statistics" button, and the system will automatically calculate the sum of the amounts of all expense items and the sum of the amounts of all income items

### Database design

![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/07.png?raw=true)

### Frame construction
![enter description here](https://github.com/xywzhht/Personal-Account-Management-System_BC_201901/blob/master/Img/10.png?raw=true)


