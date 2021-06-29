# Tournament Managment API

**Groupe :**

- Julien Lemenicier
- Adrien Laigle
- Thomas Geffray

**Database Name :** Tournament

**Script :**
```sql
CREATE DATABASE "Tournament"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
```

**3 classes :**  
- Team
- Player
- Game

**Controller :**  

- Player Controller (100%) => /players
- Team Controller (75%) => /teams
- Game Controller (0%)

**Lien vers la doc API :** http://localhost:8081/swagger-ui/index.html#/

**Security :**

*Organisateur :*  
Droits :  
- GET
- DELETE
- PUT
- POST
```json
    {
        "identifiant": "organisateur",
        "mot de passe": "root"
    }
```

*Visiteur :*  
Droits :  
- GET
```json
    {
        "identifiant": "visiteur",
        "mot de passe": "root"
    }
```