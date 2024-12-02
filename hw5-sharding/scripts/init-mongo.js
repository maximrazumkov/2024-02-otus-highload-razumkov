// init-mongo.js
db = db.getSiblingDB("chatApp");

// Enable sharding for the database
sh.enableSharding("chatApp");

// Create and shard dialogs collection
db.dialogs.createIndex({ _id: "hashed" });
sh.shardCollection("chatApp.dialogs", { _id: "hashed" });

// Create and shard messages collection
db.messages.createIndex({ dialogId: "hashed" });
sh.shardCollection("chatApp.messages", { dialogId: "hashed" });

print("Database initialization completed!");