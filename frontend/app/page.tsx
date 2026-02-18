import Link from "next/link";

export default function Home() {
  return (
    <div className="flex min-h-screen items-center justify-center bg-zinc-50 font-sans text-gray-800">
      <main className="flex min-h-screen w-full max-w-5xl flex-col items-center justify-between py-[400px] px-16 items-start md:items-center">
        <div className="">
          <h1 className="font-extrabold text-7xl text-center">🌿Spring Boot + Next.js</h1>
          <div className="mt-8">
            <p className="text-center text-xl">This is a simple example of a Spring Boot backend with a Next.js frontend.</p>
          </div>
          <div className="mt-8 flex gap-4 justify-center">
            <Link href="/users" className="px-4 py-2 bg-green-600 text-white rounded">Go to Users</Link>
            <Link href="/posts" className="px-4 py-2 bg-transparent text-green-600 rounded border border-green-600">Go to Posts</Link>
          </div>
        </div>
      </main>
    </div>
  );
}
