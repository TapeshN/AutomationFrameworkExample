const axios = require('axios');
const chai = require('chai');
const expect = chai.expect;

describe('JSONPlaceholder /posts API', function() {
    const baseURL = 'https://jsonplaceholder.typicode.com/posts';

    this.timeout(10000);

    it('should retrieve all posts', async function() {
        const response = await axios.get(baseURL);

        expect(response.status).to.equal(200);
        expect(response.data).to.be.an('array');
        expect(response.data.length).to.be.at.least(1);
    });

    it('should retrieve a specific post by ID', async function() {
        const postId = 1;
        const response = await axios.get(`${baseURL}/${postId}`);

        expect(response.status).to.equal(200);
        expect(response.data).to.be.an('object');
        expect(response.data.id).to.equal(postId);
    });

    it('should return 404 for a post that does not exist', async function() {
        const invalidPostId = 999999;

        try {
            await axios.get(`${baseURL}/${invalidPostId}`);
        } catch (error) {
            expect(error.response.status).to.equal(404);
        }
    });

    it('should create a new post', async function() {
        const newPost = {
            title: 'Example Test: Post',
            body: 'success!',
            userId: 1
        };

        const response = await axios.post(baseURL, newPost);

        expect(response.status).to.equal(201);
        expect(response.data.title).to.equal(newPost.title);
        expect(response.data.body).to.equal(newPost.body);
        expect(response.data.userId).to.equal(newPost.userId);
    });

    // More tests can be added to cover further CRUD operations, and edge cases.
});