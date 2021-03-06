/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2016 (13.0.4001)
    Source Database Engine Edition : Microsoft SQL Server Express Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server 2017
    Target Database Engine Edition : Microsoft SQL Server Standard Edition
    Target Database Engine Type : Standalone SQL Server
*/
USE [master]
GO
/****** Object:  Database [Netflix]    Script Date: 15/01/2018 17:12:22 ******/
CREATE DATABASE [Netflix]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Netflix', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\Netflix.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Netflix_log', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\Netflix_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Netflix] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Netflix].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Netflix] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Netflix] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Netflix] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Netflix] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Netflix] SET ARITHABORT OFF 
GO
ALTER DATABASE [Netflix] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Netflix] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Netflix] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Netflix] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Netflix] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Netflix] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Netflix] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Netflix] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Netflix] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Netflix] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Netflix] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Netflix] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Netflix] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Netflix] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Netflix] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Netflix] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Netflix] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Netflix] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Netflix] SET  MULTI_USER 
GO
ALTER DATABASE [Netflix] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Netflix] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Netflix] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Netflix] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Netflix] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Netflix] SET QUERY_STORE = OFF
GO
USE [Netflix]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [Netflix]
GO
/****** Object:  Table [dbo].[Content]    Script Date: 15/01/2018 17:12:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Content](
	[ContentNr] [int] NOT NULL,
	[Title] [nvarchar](50) NULL,
	[Duration] [int] NULL,
	[ContentType] [nvarchar](30) NULL,
	[Series] [nvarchar](50) NULL,
	[Season] [int] NULL,
	[Genre] [nvarchar](30) NULL,
	[Language] [nvarchar](30) NULL,
	[MinimumAge] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ContentNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profile]    Script Date: 15/01/2018 17:12:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profile](
	[Name] [nvarchar](30) NOT NULL,
	[BirthDate] [date] NULL,
	[Email] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Email] ASC,
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Series]    Script Date: 15/01/2018 17:12:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Series](
	[Title] [nvarchar](50) NOT NULL,
	[Genre] [nvarchar](30) NULL,
	[MinimumAge] [int] NULL,
	[Language] [nvarchar](30) NULL,
	[RelatedSeries] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Stream]    Script Date: 15/01/2018 17:12:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stream](
	[SubscriptionEmail] [nvarchar](30) NOT NULL,
	[ProfileName] [nvarchar](30) NOT NULL,
	[ContentType] [nvarchar](30) NULL,
	[ContentNr] [int] NOT NULL,
	[PercentageWatched] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SubscriptionEmail] ASC,
	[ProfileName] ASC,
	[ContentNr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subscription]    Script Date: 15/01/2018 17:12:22 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subscription](
	[Email] [nvarchar](30) NOT NULL,
	[Name] [nvarchar](30) NULL,
	[Postalcode] [nvarchar](30) NULL,
	[City] [nvarchar](30) NULL,
	[Street] [nvarchar](30) NULL,
	[Housenumber] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (1, N'Jack Reacher', 134, N'Movie', NULL, NULL, N'Action', N'English', 16)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (2, N'Arrival', 113, N'Movie', NULL, NULL, N'Science Fiction', N'English', 13)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (3, N'Interstellar', 169, N'Movie', NULL, NULL, N'Science Fiction', N'English', 13)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (4, N'The Matrix', 136, N'Movie', NULL, NULL, N'Action', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (5, N'The Martian', 141, N'Movie', NULL, NULL, N'Science Fiction', N'English', 13)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (6, N'Fight Club', 139, N'Movie', NULL, NULL, N'Drama', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (7, N'John Wick', 101, N'Movie', NULL, NULL, N'Action', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (8, N'The Wolf of Wall Street', 180, N'Movie', NULL, NULL, N'Comedy', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (9, N'Django Unchained', 165, N'Movie', NULL, NULL, N'Drama', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (10, N'Mad Max: Fury Road', 120, N'Movie', NULL, NULL, N'Action', N'English', 17)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (11, N'Wall-E', 98, N'Movie', NULL, NULL, N'Animation', N'English', 3)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (101, N'Pilot', 45, N'Episode', N'Arrow', 1, NULL, NULL, NULL)
INSERT [dbo].[Content] ([ContentNr], [Title], [Duration], [ContentType], [Series], [Season], [Genre], [Language], [MinimumAge]) VALUES (102, N'New Friends', 45, N'Episode', N'Arrow', 1, NULL, NULL, NULL)
INSERT [dbo].[Profile] ([Name], [BirthDate], [Email]) VALUES (N'Floris', CAST(N'2000-01-01' AS Date), N'florisbotermans@gmail.com')
INSERT [dbo].[Profile] ([Name], [BirthDate], [Email]) VALUES (N'Anton', CAST(N'2001-03-01' AS Date), N'janbeltermann@gmail.com')
INSERT [dbo].[Profile] ([Name], [BirthDate], [Email]) VALUES (N'Jan', CAST(N'1998-05-02' AS Date), N'janbeltermann@gmail.com')
INSERT [dbo].[Series] ([Title], [Genre], [MinimumAge], [Language], [RelatedSeries]) VALUES (N'Arrow', N'Thriller', 16, N'English', N'Flash')
INSERT [dbo].[Series] ([Title], [Genre], [MinimumAge], [Language], [RelatedSeries]) VALUES (N'Flash', N'Action', 16, N'English', N'Arrow')
INSERT [dbo].[Series] ([Title], [Genre], [MinimumAge], [Language], [RelatedSeries]) VALUES (N'The Punisher', N'Action', 18, N'English', N'Arrow')
INSERT [dbo].[Stream] ([SubscriptionEmail], [ProfileName], [ContentType], [ContentNr], [PercentageWatched]) VALUES (N'janbeltermann@gmail.com', N'Jan', N'Movie', 1, 100)
INSERT [dbo].[Stream] ([SubscriptionEmail], [ProfileName], [ContentType], [ContentNr], [PercentageWatched]) VALUES (N'janbeltermann@gmail.com', N'Jan', N'Episode', 101, 100)
INSERT [dbo].[Stream] ([SubscriptionEmail], [ProfileName], [ContentType], [ContentNr], [PercentageWatched]) VALUES (N'janbeltermann@gmail.com', N'Jan', N'Episode', 102, 64)
INSERT [dbo].[Subscription] ([Email], [Name], [Postalcode], [City], [Street], [Housenumber]) VALUES (N'florisbotermans@gmail.com', N'Botermans', N'5126GS', N'Gilze', N'Oranjestraat', 29)
INSERT [dbo].[Subscription] ([Email], [Name], [Postalcode], [City], [Street], [Housenumber]) VALUES (N'janbeltermann@gmail.com', N'Belterman', N'5126ED', N'Gilze', N'Den Dries', 63)
ALTER TABLE [dbo].[Content]  WITH CHECK ADD FOREIGN KEY([Series])
REFERENCES [dbo].[Series] ([Title])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Profile]  WITH CHECK ADD FOREIGN KEY([Email])
REFERENCES [dbo].[Subscription] ([Email])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Series]  WITH CHECK ADD FOREIGN KEY([RelatedSeries])
REFERENCES [dbo].[Series] ([Title])
GO
ALTER TABLE [dbo].[Stream]  WITH CHECK ADD FOREIGN KEY([SubscriptionEmail], [ProfileName])
REFERENCES [dbo].[Profile] ([Email], [Name])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Stream]  WITH CHECK ADD FOREIGN KEY([ContentNr])
REFERENCES [dbo].[Content] ([ContentNr])
ON UPDATE CASCADE
GO
USE [master]
GO
ALTER DATABASE [Netflix] SET  READ_WRITE 
GO
