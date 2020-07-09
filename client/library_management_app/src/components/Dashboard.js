import React, { Component } from 'react';

import axios from 'axios';
import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';

class Dashboard extends Component {

    state = {
        books: [],
        newBookData: {
            bookName: '',
            author: '',
            yearOfPublication: ''
        },
        editBookData: {
            bookId: '',
            bookName: '',
            author: '',
            yearOfPublication: ''
        },
        newBookModal: false,
        editBookModal: false
    }

    componentDidMount() {
        this._refreshBooks();
    }

    toggleNewBookModal = () => {
        this.setState({
            newBookModal: !this.state.newBookModal
        });
    }

    toggleEditBookModal = () => {
        this.setState({
            editBookModal: !this.state.editBookModal
        });
    }

    addBook = () => {
        axios.post('http://localhost:8100/library/add_book', this.state.newBookData).then((response) => {
            let { books } = this.state;

            books.push(response.data);
            this._refreshBooks();
            this.setState({
                books,
                newBookModal: false,
                newBookData: {
                    bookName: '',
                    author: '',
                    yearOfPublication: ''
                }
            });
        });
    }

    updateBook = () => {

        axios.put('http://localhost:8100/library/update_book', this.state.editBookData).then((response) => {

            this._refreshBooks();

            this.setState({
                editBookModal: false,
                editBookData: {
                    bookId: '',
                    bookName: '',
                    author: '',
                    yearOfPublication: ''
                }
            })
        });
    }

    editBook = (bookId, bookName, author, yearOfPublication) => {
        this.setState({
            editBookData: { bookId, bookName, author, yearOfPublication }, editBookModal: !this.state.editBookModal
        });
    }

    deleteBook(bookId) {
        axios.post(`http://localhost:8100/library/${bookId}`).then((response) => {
            this._refreshBooks();
        });
    }

    _refreshBooks = () => {
        axios.get('http://localhost:8100/library/books').then((response) => {
            this.setState({
                books: response.data.bookList
            })
        });
    }

    render() {

        let books = this.state.books ? this.state.books.map(book => {
            return (
                <tr key={book.bookId}>
                    <td>{book.bookName}</td>
                    <td>{book.author}</td>
                    <td>{book.yearOfPublication}</td>
                    <td>
                        <Button color="success" size="sm" className="mr-2" onClick={() => this.editBook(book.bookId, book.bookName, book.author, book.yearOfPublication)}>Edit</Button>
                        <Button color="danger" size="sm" onClick={() => this.deleteBook(book.bookId)}>Delete</Button>
                    </td>
                </tr>
            )
        }) : null;

        return (
            <div className="Dash container">
                <h1>Library Management Application</h1>
                <Button className="my-3" color="primary" onClick={this.toggleNewBookModal}>Add Book</Button>
                <Modal isOpen={this.state.newBookModal} toggle={this.toggleNewBookModal}>
                    <ModalHeader toggle={this.toggleNewBookModal}>Add New Book</ModalHeader>
                    <ModalBody>
                        <FormGroup>
                            <Label for="bookName">Title</Label>
                            <Input id="bookName" value={this.state.newBookData.bookName} onChange={(e) => {
                                let { newBookData } = this.state;

                                newBookData.bookName = e.target.value;

                                this.setState({ newBookData });
                            }} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="author">Author</Label>
                            <Input id="author" value={this.state.newBookData.author} onChange={(e) => {
                                let { newBookData } = this.state;

                                newBookData.author = e.target.value;

                                this.setState({ newBookData });
                            }} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="yearOfPublication">Published On</Label>
                            <Input id="yearOfPublication" value={this.state.newBookData.yearOfPublication} onChange={(e) => {
                                let { newBookData } = this.state;

                                newBookData.yearOfPublication = e.target.value;

                                this.setState({ newBookData });
                            }} />
                        </FormGroup>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.addBook}>Add Book</Button>{' '}
                        <Button color="secondary" onClick={this.toggleNewBookModal}>Cancel</Button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={this.state.editBookModal} toggle={this.toggleEditBookModal}>
                    <ModalHeader toggle={this.toggleEditBookModal}>Update This Book</ModalHeader>
                    <ModalBody>
                        <FormGroup>
                            <Label for="bookName">Title</Label>
                            <Input id="bookName" value={this.state.editBookData.bookName} onChange={(e) => {
                                let { editBookData } = this.state;

                                editBookData.bookName = e.target.value;

                                this.setState({ editBookData });
                            }} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="author">Author</Label>
                            <Input id="author" value={this.state.editBookData.author} onChange={(e) => {
                                let { editBookData } = this.state;

                                editBookData.author = e.target.value;

                                this.setState({ editBookData });
                            }} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="yearOfPublication">Published On</Label>
                            <Input id="yearOfPublication" value={this.state.editBookData.yearOfPublication} onChange={(e) => {
                                let { editBookData } = this.state;

                                editBookData.yearOfPublication = e.target.value;

                                this.setState({ editBookData });
                            }} />
                        </FormGroup>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.updateBook}>Update Book</Button>
                        <Button color="secondary" onClick={this.toggleEditBookModal}>Cancel</Button>
                    </ModalFooter>
                </Modal>
                <Table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Published On</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {books}
                    </tbody>
                </Table>
            </div>
        );
    }

}

export default Dashboard;