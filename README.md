# Telstra Starter Repo

## SIM Card Activation

**Endpoint:** `POST /api/v1/activate`

Activates a SIM card by sending the ICCID to an actuator service.

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
- `200 OK`: SIM card activated successfully
- `400 Bad Request`: Invalid ICCID or email format
- `500 Internal Server Error`: Actuator service error
