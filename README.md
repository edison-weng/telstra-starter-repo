# Telstra Starter Repo

## SIM Card Activation

**Endpoint:** `POST /api/v1/activate`

Activates a SIM card by sending the ICCID to an actuator service and stores the record in the database.

**Request Body:**
```json
{
  "iccid": "string",
  "customerEmail": "string"
}
```

**Request Fields:**
- `iccid` (required): The ICCID of the SIM card to activate
- `customerEmail` (required): Valid email address of the customer

**Responses:**
- `201 Created`: SIM card activated successfully with activation ID
- `400 Bad Request`: Invalid ICCID or email format
- `500 Internal Server Error`: Actuator service error

## Query SIM Card

**Endpoint:** `GET /api/v1/query?simCardId={id}`

Retrieves information about a previously activated SIM card from the database.

**Request Parameters:**
- `simCardId` (required): The ID of the SIM card record to retrieve

**Response Body:**
```json
{
  "iccid": "string",
  "customerEmail": "string",
  "active": boolean
}
```

**Responses:**
- `200 OK`: Record found and returned
- `404 Not Found`: No record found with the given ID

## Data Persistence

The application uses an H2 in-memory database to store SIM card activation records. Each record contains:

- ICCID: The SIM card identifier
- Customer Email: The email of the customer
- Active: Whether the activation was successful
