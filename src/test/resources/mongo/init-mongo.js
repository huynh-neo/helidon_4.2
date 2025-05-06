db = db.getSiblingDB("analyticsdb");

db.createUser({
  user: "mongoadmin",
  pwd: "secret",
  roles: [{ role: "readWrite", db: "analyticsdb" }]
});

db.click_analytics.insertOne({
  linkId: "test123",
  timestamp: new Date(),
  userAgent: "JUnit"
});
