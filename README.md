# My Links

A API to management application links

## Diagram
    
```mermaid

classDiagram
    class Link {
        +String link
        +String name
        +String description
        +String icon
    }

    class Category {
        +String name
        +String description
        +String parent
    }

    Link --> Category : belongsTo
```