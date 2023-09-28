export interface User {

    id: number;

    name: string;

    password: string;

}



export interface Post {

    id: number;

    title: string;

    content: string;

    author: User;

    createdAt: Date;

    updatedAt: Date;

}

