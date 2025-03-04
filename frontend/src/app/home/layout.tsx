import type { Metadata } from "next";
import { Inter } from "next/font/google";
import { ThemeProvider } from "@/components/theme-provider";
import { Sidebar } from "@/components/root-sidebar-layout";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Finance App",
  description: "A cool app",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <ThemeProvider
          attribute="class"
          defaultTheme="dark"
          enableSystem
          disableTransitionOnChange
        >
          <main className="h-screen flex flex-col items-center py-[24px]">
            <div className="h-full w-full max-w-[1800px] grid grid-cols-[200px_auto] gap-5">
              <Sidebar />
              {children}
            </div>
          </main>
        </ThemeProvider>
      </body>
    </html>
  );
}