CREATE
  EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE
  TABLE
    posts(
      id uuid UNIQUE DEFAULT uuid_generate_v4(),
      author_id VARCHAR(255) NOT NULL,
      created TIMESTAMP WITH TIME ZONE NOT NULL,
      updated TIMESTAMP WITH TIME ZONE,
      title VARCHAR(100) NOT NULL,
      summary VARCHAR(200) NOT NULL,
      markdown TEXT NOT NULL,
      image_url VARCHAR(2083) NOT NULL,
      PRIMARY KEY(id)
    );

CREATE
  INDEX idx_posts_author_id ON
  posts(author_id);

CREATE
  INDEX idx_posts_title ON
  posts(title);

CREATE
  INDEX idx_posts_summary ON
  posts(summary);

CREATE
  INDEX idx_posts_markdown ON
  posts(markdown);