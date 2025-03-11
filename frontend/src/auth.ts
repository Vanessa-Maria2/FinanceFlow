import NextAuth from "next-auth"
import Credentials from "next-auth/providers/credentials"

export const { handlers, signIn, signOut, auth } = NextAuth({
  pages: {
    signIn: "/login",
  },
  providers: [
    Credentials({
      credentials: {
        email: { label: "Email" },
        password: { label: "Password", type: "password" },
      },
      authorize: async (credentials) => {
        try {
          const response = await fetch("http://localhost:8080/auth/", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              email: credentials?.email,
              password: credentials?.password,
            }),
          });

          if (!response.ok) {
            throw new Error("Credenciais inválidas");
          }

          const user = await response.json();
          console.log("Usuário logado:", user);

          return {
            id: String(user.id),
            name: user.name,
            email: user.email,
          };
        } catch (error) {
          console.error("Erro na autenticação:", error);
          return null;
        }
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        token.id = user.id;
        token.name = user.name;
        token.email = user.email;
      }
      return token;
    },

    async session({ session, token }) {
      session.user = {
        ...session.user,
        id: String(token.id), 
        name: token.name ?? "Usuário",
        email: token.email ?? "",
      };
      return session;
    },
  },

  session: {
    strategy: "jwt",
  },
});