# List of products stored as dictionaries
products = [
    {"name": "Pen", "stock": 25},
    {"name": "Notebook", "stock": 8},
    {"name": "Pencil", "stock": 5},
    {"name": "Eraser", "stock": 15},
    {"name": "Marker", "stock": 3}
]

print("Products with stock less than 10:\n")

found = False

# Check stock condition
for product in products:
    if product["stock"] < 10:
        print(f"Product Name: {product['name']}, Stock: {product['stock']}")
        found = True

if not found:
    print("No products with stock less than 10.")
