import ky from "ky";

let getAccessToken = () => Promise.resolve('');

export function setAccessTokenGetter(getter) {
  getAccessToken = getter;
}

export const api = ky.create({
  prefixUrl: 'http://localhost:8080/api',
  hooks: {
    beforeRequest: [
        async request => {
          const token = await getAccessToken();
          request.headers.set('Authorization', `Bearer ${token}`);
        },
    ],
  },
});
