## Vulpes Model

This project provides a unified model structure for storing various data used in the Vulpes project.  
It is utilized by both `vulpes-backend` and `vulpes-generator` to ensure consistency while avoiding code duplication.

## Why SQL and not MongoDB?

The initial plan was to use MongoDB for its flexible, schema-less storage.  
However, during development, it became clear that this choice was not ideal. The data structure was not as flexible as
expected, and the advantages of a schema-less database were not utilized.

Additionally, since the data is inherently structured rather than unstructured, a SQL database proved to be the more
suitable choice.

## Contributing

If you want to contribute to this project, please follow the guidelines in the [CONTRIBUTING.md](CONTRIBUTING.md) file.